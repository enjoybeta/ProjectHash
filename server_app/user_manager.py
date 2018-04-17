import sys
import json
import cassandra
from cassandra.cluster import Cluster

'''user_manager ccontains four functions that are needed for user profile'''

'''
	sign_up function takes a json object that contains user's sign up information
	and puts the information into database and returns success message.
	If username already exist, return fail message.
	@request: json object of user's sign up data (username, email, password)
'''
def sign_up(request):
	#create connection to database
	cluster = Cluster()
	session = cluster.connect('hash')

	#get user's sign up data
	data = json.loads(request.decode('utf-8'))
	username = data["username"]
	email = data["email"]
	password = data["password"]

	#check if the username already exists
	rows = session.execute('''
				SELECT * from user where username = %s allow filtering
				''',
				(username,)
			)
	if rows: 
		return "Username already exists!"
	else:
		#insert user information to database
		session.execute('''
				INSERT INTO user (id, username, email, password)
				VALUES (now(), %s, %s, %s)
				''',
				(username, email, password)
			)
	return "Sign up success!"
	#close database connection
	cluster.shutdown()


'''
	login function takes a json object that contains user's login information
	return success message if username exist and passwor is correct. Otherwise,
	return fail message
	@request: json object of user's login data (username, password)
'''
def login(request):
	#create connection to database
	cluster = Cluster()
	session = cluster.connect('hash')

	#get user's login data
	data = json.loads(request.decode('utf-8'))
	username = data["username"]
	password = data["password"]

	#check if the username exists
	rows = session.execute('''
				SELECT * from user where username = %s allow filtering
				''',
				(username,)
			)
	if rows:
		for row in rows:
			#check if the password is correct
			if password == row.password:	
				return "Login success!"
			else:
				return "Wrong username or password!"

	else:
		return "Wrong username or password!"
	#close database connection
	cluster.shutdown()


'''
	upload function takes a json object that contains user's information that need 
	to be updated in the database and update the information to the database
	If username or password is wrong, return fail message.
	@request: json object of user's data (username, password, favourite, ingredients)
'''
def upload(request):
	#create connection to database
	cluster = Cluster()
	session = cluster.connect('hash')

	#get user's upload data
	data = json.loads(request.decode('utf-8'))
	username = data["username"]
	password = data["password"]
	favourite = data["favourite"]
	ingredients = data["ingredients"]

	#check if the username exists
	rows = session.execute('''
				SELECT * from user where username = %s allow filtering
				''',
				(username,)
			)
	if rows:
		for row in rows:
			#check if the password is correct
			if password == row.password:
				#update the data in the database	
				session.execute('''
					UPDATE user SET favourite = %s, ingredients = %s WHERE username = %s
					''',
					(favourite, ingredients, username)
				)
				return "Upload success!"
			else:
				return "Wrong username or password!"

	else:
		return "Wrong username or password!"
	#close database connection
	cluster.shutdown()


'''
	download function takes a json object that contains user's information
	and get the data of corresponding user from database and returns the data.
	If username or password is wrong, return fail message.
	@request: json object of user's sign up data
'''
def download(request):
	#create connection to database
	cluster = Cluster()
	session = cluster.connect('hash')

	#get user's information
	data = json.loads(request.decode('utf-8'))
	username = data["username"]
	password = data["password"]

	#check username
	rows = session.execute('''
				SELECT * from user where username = %s allow filtering
				''',
				(username,)
			)
	if rows:
		for row in rows:
			#check password
			if password == row.password:	
				#gget data and return
				return_data = { 'username': row.username,
							'favourite': row.favourite,
							'ingredients': row.ingredients}
				json_return = json.dumps(return_data)
				return json_return
			else:
				return "Wrong username or password!"

	else:
		return "Wrong username or password!"
	#close database connection
	cluster.shutdown()