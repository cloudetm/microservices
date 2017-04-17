puts "** if !() **"

temperature = 75
if !(temperature > 75)
  puts "should feel nice outside"
end

puts "** unless **"

unless temperature > 75
  puts "should feel nice outside"
end

# output:
# ** if !() **
# should feel nice outside
# ** unless **
# should feel nice outside
