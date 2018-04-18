import unittest
import user_manager
import clean_user
import json

class WebManagerTest(unittest.TestCase):

    def test_signup(self):
        clean_user.clean_user()
        string1 = user_manager.sign_up(json.dumps({"username": "test1", "password": "t1", "email": "test1@t.com"}))
        string2 = user_manager.sign_up(json.dumps({"username": "test1", "password": "t1", "email": "test1@t.com"}))
        string3 = user_manager.sign_up(json.dumps({"username": "test2", "password": "t2", "email": "test2@t.com"}))
        string4 = user_manager.sign_up(json.dumps({"username": "", "password": "t1", "email": "test1@t.com"}))
        string5 = user_manager.sign_up(json.dumps({"username": "test1", "password": "", "email": "test1@t.com"}))
        string6 = user_manager.sign_up(json.dumps({"username": "test1", "password": "t1", "email": ""}))
        self.assertEqual(string1, "Sign up success!")
        self.assertEqual(string2, "Username already exists!")
        self.assertEqual(string3, "Sign up success!")
        self.assertEqual(string4, "Username can not be empty!")
        self.assertEqual(string5, "Password can not be empty!")
        self.assertEqual(string6, "Email can not be empty!")
        clean_user.clean_user()

    def test_login(self):
        clean_user.clean_user()
        user_manager.sign_up(json.dumps({"username": "test1", "password": "t1", "email": "test1@t.com"}))
        user_manager.sign_up(json.dumps({"username": "test2", "password": "t2", "email": "test2@t.com"}))
        string1 = user_manager.login(json.dumps({"username": "test1", "password": "t1"}))
        string2 = user_manager.login(json.dumps({"username": "test1", "password": "t2"}))
        string3 = user_manager.login(json.dumps({"username": "test2", "password": "t2"}))
        string4 = user_manager.login(json.dumps({"username": "test3", "password": "t3"}))
        string5 = user_manager.login(json.dumps({"username": "", "password": "t1"}))
        string6 = user_manager.login(json.dumps({"username": "test1", "password": ""}))
        self.assertEqual(string1,"Login success!")
        self.assertEqual(string2,"Wrong username or password!")
        self.assertEqual(string3,"Login success!")
        self.assertEqual(string4,"Wrong username or password!")
        self.assertEqual(string5,"Username can not be empty!")
        self.assertEqual(string6,"Password can not be empty!")
        clean_user.clean_user()

    def test_upload_download(self):
        clean_user.clean_user()
        user_manager.sign_up(json.dumps({"username": "test1", "password": "t1", "email": "test1@t.com"}))
        user_manager.sign_up(json.dumps({"username": "test2", "password": "t2", "email": "test2@t.com"}))
        string1 = user_manager.upload(json.dumps({"username": "test1", "password": "t1", "favorite": "favorite test", "ingredients": "ingreidents test"}))
        string2 = user_manager.upload(json.dumps({"username": "test1", "password": "t2", "favorite": "favorite test", "ingredients": "ingreidents test"}))
        string3 = user_manager.upload(json.dumps({"username": "test2", "password": "t2", "favorite": "favorite test2", "ingredients": "ingreidents test2"}))
        string4 = user_manager.download(json.dumps({"username": "test1", "password": "t1"}))
        string5 = user_manager.download(json.dumps({"username": "test2", "password": "t2"}))
        string6 = user_manager.download(json.dumps({"username": "test1", "password": "t2"}))
        string7 = user_manager.download(json.dumps({"username": "test3", "password": "t1"}))
        self.assertEqual(string1,"Upload success!")
        self.assertEqual(string2,"Wrong username or password!")
        self.assertEqual(string3,"Upload success!")
        self.assertEqual(string4,json.dumps({"username": "test1", "favorite": "favorite test", "ingredients": "ingreidents test"}))
        self.assertEqual(string5,json.dumps({"username": "test2", "favorite": "favorite test2", "ingredients": "ingreidents test2"}))
        self.assertEqual(string6,"Wrong username or password!")
        self.assertEqual(string7,"Wrong username or password!")
        clean_user.clean_user()


if __name__ == '__main__':
    unittest.main()