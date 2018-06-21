import json
import cassandra
import sys
from cassandra.cluster import Cluster
import os

def main(){
	#create database connection
	cluster = Cluster()
	#use keyspace 'hash'
	session = cluster.connect()

    #!!!
    #CREATE KEYSPACE IF NOT EXITSTS hash WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor':1};
    session.execute("""
                    CREATE KEYSPACE IF NOT EXITSTS hash WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor': '1'}
                    """)

    
    session.execute('''
                    CREATE TABLE public_recipe (
                        id text,
                        name text,
                        time int,
                        imageurl text,
                        ingredients list<text>,
                        numberofserving int,
                        flavor text,
                        instruction text,
                        PRIMARY KEY(id) )
                    ''')
                    
    session.execute('''
                    CREATE TABLE user (
                        id uuid,
                        username text,
                        email text,
                        password text,
                        favorite text,
                        ingredients text,
                        PRIMARY KEY(username) )
                    ''')
                    
    session.execute('''
                    CREATE custom index fn_contains on public_recipe(name) using 'org.apache.cassandra.index.sasi.SASIIndex' with OPTIONS = {
                        'mode': 'CONTAINS', 
                        'analyzer_class': 'org.apache.cassandra.index.sasi.analyzer.NonTokenizingAnalyzer',
                        'case_sensitive': 'false'}
                    ''')

	#close database connection
	cluster.shutdown()
}

if __name__ == '__main__':
    main()