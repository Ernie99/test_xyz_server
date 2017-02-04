print("hello_world")

import http.server
import socketserver
from random import randint
from datetime import datetime

class MyHandler(http.server.BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header('Content-type','text/html')
        self.send_header("Access-Control-Allow-Origin", "*")
        self.end_headers()
        # Send the html message
        # payload = "" + str(randint(0,9)) + "," + randint(10,19) "," + randint(20,29) + str(datetime.now())
        payload = str(randint(0,9)) + "," + \
            str(randint(10,19)) + "," + \
            str(randint(20,29)) + "," + \
            str(datetime.now())
        self.wfile.write(payload.encode('utf8').strip())
        return

PORT = 8888

Handler = http.server.SimpleHTTPRequestHandler

httpd = socketserver.TCPServer(("", PORT), MyHandler)

print("serving at port", PORT)
httpd.serve_forever()