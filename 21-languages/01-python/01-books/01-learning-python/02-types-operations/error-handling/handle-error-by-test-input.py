while True:
    reply = input('Enter text:')
    if reply == 'stop':
        break
    elif not reply.isdigit():
        print('Bad!' * 3)
    else:
        print(int(reply) ** 2)
print('Bye')

# Enter text:'2'
# 4
# Enter text:'a'
# Bad!Bad!Bad!Bad!Bad!Bad!Bad!Bad!
# Enter text:'stop'
# Bye
