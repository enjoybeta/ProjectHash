import sys
import json
import cassandra
from cassandra.cluster import Cluster


def sign_up(request):
	#create connection to database
	cluster = Cluster()
	session = cluster.connect('hash')
	data = json.load(request.decode('utf-8'))
	username = data["username"]
	email = data["email"]
	password = data["password"]
	rows = session.execute('''
				SELECT * from user where username = %s allow filtering
				''',
				(username,)
			)
	if rows:
		print "Username already exists!"
		return "Username already exists!"
	else:
		session.execute('''
				INSERT INTO user (id, username, email, password)
				VALUES (now(), %s, %s, %s)
				''',
				(username, email, password)
			)
	print "Sign up success!"
	return "Sign up success!"
	#close database connection
	cluster.shutdown()


def login(request):
	#create connection to database
	cluster = Cluster()
	session = cluster.connect('hash')
	data = json.load(request.decode('utf-8'))
	username = data["username"]
	password = data["password"]
	rows = session.execute('''
				SELECT * from user where username = %s allow filtering
				''',
				(username,)
			)
	if rows:
		for row in rows:
			if password == row.password:	
				print "Login success!"
				return "Login success!"
			else:
				print "Wrong username or password!"
				return "Wrong username or password!"

	else:
		print "Wrong username or password!"
		return "Wrong username or password!"
	#close database connection
	cluster.shutdown()


def upload(request):
	#create connection to database
	cluster = Cluster()
	session = cluster.connect('hash')
	data = json.load(request.decode('utf-8'))
	username = data["username"]
	password = data["password"]
	favourite = data["favourite"]
	ingredients = data["ingredients"]
	rows = session.execute('''
				SELECT * from user where username = %s allow filtering
				''',
				(username,)
			)
	if rows:
		for row in rows:
			if password == row.password:	
				session.execute('''
					UPDATE user SET favourite = %s, ingredients = %s WHERE username = %s
					''',
					(favourite, ingredients, username)
				)
				print "Upload success!"
				return "Upload success!"
			else:
				print "Wrong username or password!"
				return "Wrong username or password!"

	else:
		print "Wrong username or password!"
		return "Wrong username or password!"
	#close database connection
	cluster.shutdown()


def download(request):
	#create connection to database
	cluster = Cluster()
	session = cluster.connect('hash')
	data = json.load(request.decode('utf-8'))
	username = data["username"]
	password = data["password"]
	rows = session.execute('''
				SELECT * from user where username = %s allow filtering
				''',
				(username,)
			)
	if rows:
		for row in rows:
			if password == row.password:	
				return_data = { 'username': row.username,
							'favourite': row.favourite,
							'ingredients': row.ingredients}
				json_return = json.dumps(return_data)
				print json_return
				return json_return
			else:
				print "Wrong username or password!"
				return "Wrong username or password!"

	else:
		print "Wrong username or password!"
		return "Wrong username or password!"
	#close database connection
	cluster.shutdown()