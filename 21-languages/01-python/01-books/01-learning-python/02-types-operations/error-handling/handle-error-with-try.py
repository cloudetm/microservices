while True:
    reply = input('Enter text:')
    if reply == 'stop': break
    try:
        num = int(reply)
    except Exception, e:
        print(e)
        print('Bad!' * 3)
    else:
        print(num ** 2)
print('Bye')

# Enter text:'3'
# 9
# Enter text:'a'
# invalid literal for int() with base 10: 'a'
# Bad!Bad!Bad!
# Enter text:'stop'
# Bye
