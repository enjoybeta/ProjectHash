import sys
import cassandra
from cassandra.cluster import Cluster


def clean_user():
	#create connection to database
	cluster = Cluster()
	session = cluster.connect('hash')
	session.execute('''
			truncate user
			'''
		)
	#close database connection
	cluster.shutdown()
	return