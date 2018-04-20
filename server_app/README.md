# Server application for Hash
## Environment Requirement
name | version
------------ | -------------
Ubuntu | 16.04.4 LTS
Nginx | 1.10.3
Gunicorn | 19.7.1
Cassandra | 3.11.2
Python | 3.5.2
##### Reminder: system needs at least 2GiB memory to run Cassandra smoothly

## How to initalize web service (WSGI)?
#### service cassandra start
-> start the cassandra service and providing database access
#### gunicorn -w 4 -b 127.0.0.1:5000 app:app
-> start the app.py script and broadcasting on port 127.0.0.1:5000
#### service nginx start
-> start the nginx service and broadcast to ethernet
 
## How to setup Nginx configuration?
checkout nginx_config.txt

## How to setup Nginx with SSL connection?
The simple solution we adopt is by using Certbot <br/>
checkout https://certbot.eff.org/lets-encrypt/ubuntuxenial-nginx for detailed instruction
