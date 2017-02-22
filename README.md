#### REST Endpoints
##### Generate/Shotern url
POST /restutils/api/v1/shortenurl HTTP/1.1
Host: localhost:8902
Content-Type: application/json
{
	"url":"https://github.com"
}

Response you get it shorten url. For instance: http://localhost:8902/restutils/api/v1/Zn7VL

In the case of invalid url you get the 400 Bad Request

##### Consume the ShortenUrl 
GET /restutils/api/v1/Zn7VL HTTP/1.1
Host: localhost:8902

Just trigger the generated/provided short url in the browser. For instance,
http://localhost:8902/restutils/api/v1/Zn7VL, which should forward to github.com

##### DELETE 
DELETE /restutils/api/v1/Zn7VL HTTP/1.1
Host: localhost:8902

Example: http://localhost:8902/restutils/api/v1/Zn7VL
Response 200, if successfully deleted, else 204

##### Quick notes implementation
* Implemented 3 endpoints. generate shorten url, consume using shorten url and delete
* Written simple service to manage urls in HashMap.
* Reused efficient tiny url code generation from internet. Please refer {@ShortenURL}
* Reused Apache commons validator to validate used entered url

#### How to run
* Run **/.gradlew build** It should produce executable jar under ${SOURCE}/build/libs

##### Run as jar
* java -jar shorten-url-0.1.jar


##### Testing 
* https://www.google.de/search?espv=2&q=java+8+bimap&oq=java+8+bimap&gs_l=serp.3..0.6396849.6399032.0.6399474.7.5.0.2.2.0.108.387.4j1.5.0....0...1c.1.64.serp..0.7.383...35i39k1j0i20k1.wFy8JEfWTVY