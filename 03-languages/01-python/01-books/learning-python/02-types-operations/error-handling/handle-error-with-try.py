while True:
    reply = input('Enter text:')
    if reply == 'stop': break
    try:
        num = int(reply)
    except Exception, e:
        print(e)
        print('Bad!' * 8)
    else:
        print(num ** 2)
print('Bye')
