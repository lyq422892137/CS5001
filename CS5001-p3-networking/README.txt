# CS5001-p3-networking

----------------------------------------------------
Enhancements:
1.	The server can send binary images (GIF, JPEG, and PNG). I directly use the TCP/IP's underlying layer'transmation way -binary to transfer files. 

2.	Multithreading: if the number of connections is bigger than 100, the server will sleep 100s then starts.

3.	It has a logging file (Please see the example file log.txt in the CS5001-p3-networking directory). It records the number of the request since the server starts running,roughly handling time (int UTC), and a related request and response.

4.	It supports method:
(1)	DELETE: delete a file in the root dir. 
A “DELETE” request does not mean the server must delete the file.

It should be controlled by a certain way.

The response “200” does not mean the server has deleted the file, just telling the client that the server has received this request.

However, to simplify this progress, in this program, the server will delete the file directly.

(2)	POST: send a file to the server by a client.

If successes, “201 Created” will be sent as a response.

Or (including creating failure or the file has already existed), “202 Accepted” will be sent.


5.	When the request is “OPTIONS”, the server will response “405 Method Not Allowed”.

---------------------------------------------------------
Other Tests (in tests directory):

To make the debug process simple, I wrote two simple clients to help (I also used 'curl' command to debug but I think it is not comfortable for me enough):

(1) Client.java is a simple client which can send a HTTP request and receive text files with a HTTP Head from a server, finally it will download the received file to help me check whether this file is available.

(2) GraphClient.java is a simple client which can send a HTTP request and receive any kind of file without a HTTP Head from a server, finally it will download the received file (to make the client available, I also wrote a simple server GraphServer.java to send the file). The process is nothing about HTTP just about testing binary transmation.
