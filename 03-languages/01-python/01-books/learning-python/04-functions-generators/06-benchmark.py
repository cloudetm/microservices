import time
def timer(func, *args):  # Simplistic timing function
    start = time.clock()
    for i in range(1000):
        func(*args)
    return time.clock() - start
print(timer(pow, 2, 1000))  # Time to call pow(2, 1000) 1000 times - 0.001974
print(pow(3, 2))  # 9
print(timer(str.upper, 'spam'))  # Time to call 'spam'.upper() 1000 times - 0.00018
print(str.upper('abc'))  # ABC
