import urllib.request, urllib.parse, json

class YummlySearch:
    def __init__(self, baseSearchAddress_ = 'http://api.yummly.com/v1/api/recipes?', appId_ = '4a4d16bf', appKey_ = 'c60f961fefede4811a9f5e9709cb6829'):
        self.baseSearchAddress = baseSearchAddress_
        self.appId = appId_
        self.appKey = appKey_

    def generateSearchRequest(self, keyWords):
        if (not keyWords):
            raise RuntimeError('Key words cannot be empty')
        queryWord = keyWords[0]
        for i in range(1, len(keyWords)):
            queryWord += '+' + keyWords[i]
        parameter = {'_app_id':self.appId, '_app_key':self.appKey, 'q': queryWord}
        addressParameter = urllib.parse.urlencode(parameter)
        return self.baseSearchAddress + addressParameter

    def getSearchResult(self, searchRequest, requestTimeout = 10):
        return json.loads(urllib.request.urlopen(searchRequest, timeout = requestTimeout).read())

class RecipeSearchResult:
    pass

class Recipe:
    def __init__(self):
        pass

if __name__ == '__main__':
    search = YummlySearch()
    searchRequest = search.generateSearchRequest(['onion', 'soup'])
    with open('test_data.json', 'w') as output:
        output.write(json.dumps(search.getSearchResult(searchRequest), indent = 4))