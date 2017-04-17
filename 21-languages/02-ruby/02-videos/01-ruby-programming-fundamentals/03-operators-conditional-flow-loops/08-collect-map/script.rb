a = [1,2,3]
b = Array.new
b = a.collect{|e| e}
print b, "\n" # [1, 2, 3]

puts "** collect e*2 **"
c = Array.new
c = a.collect{|e| e*2}
print c, "\n" # [2, 4, 6]

puts "** map n*n **"
print [1,2,3].map{|n| n*n} # [1, 4, 9]

# output:
# [1, 2, 3]
# ** collect e*2 **
# [2, 4, 6]
# ** map n*n **
# [1, 4, 9]
