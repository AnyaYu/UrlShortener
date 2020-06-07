To run the app run: gradlew bootRun

The app is configured to run on port 8081, 
to change port change server.port parameter in application.properties

To test use postman collection: https://www.getpostman.com/collections/c861c1c0a05f06353378

Examples:

Wrap url:
POST
localhost:8081/api/v1/wrap
{
    "url": "{yourLongUrl}"
}

GET
localhost:8081/api/v1/wrap?longUrl={yourLongUrl}

UnWrap url:
POST
localhost:8081/api/v1/unwrap
{
	"urlString": "{yourShortUrl}"
}

GET localhost:8081/api/v1/unwrap?shortUrl={yourShortUrl}