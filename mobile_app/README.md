# Example of Json files
## ingredients.json
{
    "Fruits": [{"apple":"2 bags"}],
    "Vegetables": [],
    "Protein": [],
    "Dairy": [],
    "Grains": [],
    "Oils": []
}

## favorites.json
{"dishes":[{"name":"Onion soup","hashName":"abc0123456789"}]}
{
  "dishes":[
    {
        "name":"Onion soup",
        "hashName":"abc0123456789"
    }
  ]
}

# Example of Json message
## sendUserInfo
{
    username:"test",
    password:"12345678",
    request:"pull"
}

{
    username:"test",
    password:"12345678",
    request:"push",
    favorites:{}
}


## recvUserInfo
{
    validate:"true",
    username:"test",
    favorites:{}
}