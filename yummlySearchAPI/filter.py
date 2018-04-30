import os
import string

def isEnglish2(s):
    return s.translate(None, string.punctuation).isalnum()


def isEnglish1(s):
    try:
        s.encode(encoding='utf-8').decode('ascii')
    except UnicodeDecodeError:
        return False
    else:
        return True

if __name__ == '__main__':
    counter = 0
    for file in os.listdir('./detailedData'):
        path = os.path.join('./detailedData', file)
        f = open(path, 'r')
        content = f.read()
        f.close()
        if (not isEnglish2(content)):
            counter += 1
    print(counter)