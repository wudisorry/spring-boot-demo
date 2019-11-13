### Ensuring release of low level resources
```
CloseableHttpClient httpclient = HttpClients.createDefault();
HttpGet httpget = new HttpGet("http://localhost/");
CloseableHttpResponse response = httpclient.execute(httpget);
try {
    HttpEntity entity = response.getEntity();
    if (entity != null) {
        InputStream instream = entity.getContent();
        try {
            // do something useful
        } finally {
            instream.close();
        }
    }
} finally {
    response.close();
}
```
**The difference between closing the content stream and closing the response is that the former will attempt to keep the `underlying connection` alive by consuming the entity content while the latter immediately shuts down and discards the connection.**

Please note that the HttpEntity#writeTo(OutputStream) method is also required to ensure proper release of system resources once the entity has been fully written out. If this method obtains an instance of java.io.InputStream by calling HttpEntity#getContent(), it is also expected to close the stream in a finally clause.

**When working with streaming entities, one can use the EntityUtils#consume(HttpEntity) method to ensure that the entity content has been fully consumed and the `underlying stream` has been closed.**

**There can be situations, however, when only a small portion of the entire response content needs to be retrieved and the performance penalty for consuming the remaining content and making the connection reusable is too high, in which case one can terminate the content stream by closing the response.**
```
CloseableHttpClient httpclient = HttpClients.createDefault();
HttpGet httpget = new HttpGet("http://localhost/");
CloseableHttpResponse response = httpclient.execute(httpget);
try {
    HttpEntity entity = response.getEntity();
    if (entity != null) {
        InputStream instream = entity.getContent();
        int byteOne = instream.read();
        int byteTwo = instream.read();
        // Do not need the rest
    }
} finally {
    response.close();
}
```
The connection will not be reused, but all level resources held by it will be correctly deallocated.

###Consuming entity content
The recommended way to consume the content of an entity is by using its HttpEntity#getContent() or HttpEntity#writeTo(OutputStream) methods. 

HttpClient also comes with the EntityUtils class, which exposes several static methods to more easily read the content or information from an entity. Instead of reading the java.io.InputStream directly, one can retrieve the whole content body in a string / byte array by using the methods from this class. However, the use of EntityUtils is strongly discouraged unless the response entities originate from a trusted HTTP server and are known to be of limited length.
```
CloseableHttpClient httpclient = HttpClients.createDefault();
HttpGet httpget = new HttpGet("http://localhost/");
CloseableHttpResponse response = httpclient.execute(httpget);
try {
    HttpEntity entity = response.getEntity();
    if (entity != null) {
        long len = entity.getContentLength();
        if (len != -1 && len < 2048) {
            System.out.println(EntityUtils.toString(entity));
        } else {
            // Stream content out
        }
    }
} finally {
    response.close();
}
```
In some situations it may be necessary to be able to read entity content more than once. In this case entity content must be buffered in some way, either in memory or on disk. The simplest way to accomplish that is by wrapping the original entity with the BufferedHttpEntity class. This will cause the content of the original entity to be read into a in-memory buffer. In all other ways the entity wrapper will be have the original one.
```
CloseableHttpResponse response = <...>
HttpEntity entity = response.getEntity();
if (entity != null) {
    entity = new BufferedHttpEntity(entity);
}
```

###Producing entity content
HttpClient provides several classes for most common data containers such as string, byte array, input stream, and file: StringEntity, ByteArrayEntity, InputStreamEntity, and FileEntity.
```
File file = new File("somefile.txt");
FileEntity entity = new FileEntity(file, 
    ContentType.create("text/plain", "UTF-8"));        

HttpPost httppost = new HttpPost("http://localhost/action.do");
httppost.setEntity(entity);
```
HTML forms
```
List<NameValuePair> formparams = new ArrayList<NameValuePair>();
formparams.add(new BasicNameValuePair("param1", "value1"));
formparams.add(new BasicNameValuePair("param2", "value2"));
UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
HttpPost httppost = new HttpPost("http://localhost/handler.do");
httppost.setEntity(entity);
```
