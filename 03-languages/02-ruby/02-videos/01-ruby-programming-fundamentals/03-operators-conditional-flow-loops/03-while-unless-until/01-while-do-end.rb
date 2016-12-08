puts "** while do end **"

count = 0
max = 5
arr = []

while count < max do
  puts "count=#{count}"
  count += 1
  arr.push(count)
end

print arr, "\n"

# output
# ** while do end **
#     count=0
# count=1
# count=2
# count=3
# count=4
# [1, 2, 3, 4, 5]
