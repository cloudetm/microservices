# second.py
import first
print(first.X)  # 99 - OK: references a name in another file
first.X = 88  # But changing it can be too subtle and implicit
print(first.X)  # 88
first.log()  # 88
