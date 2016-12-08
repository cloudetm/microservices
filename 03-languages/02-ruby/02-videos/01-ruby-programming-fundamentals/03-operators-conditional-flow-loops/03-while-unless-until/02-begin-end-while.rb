puts "** while - begin end while **"

max = 5
count = 0
arr = []
begin
  puts "count=#{count}"
  count += 1
  arr.push(count)
end while count < max

print arr, "\n"

# output:
# ** while - begin end while **
# count=0
# count=1
# count=2
# count=3
# count=4
# [1, 2, 3, 4, 5]
