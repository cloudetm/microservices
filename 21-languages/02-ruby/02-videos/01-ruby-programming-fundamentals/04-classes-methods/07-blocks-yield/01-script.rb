puts "** use {} for one line code **"
[1,2,3].each {|num| print "#{num}!"} # {} is a block as anonymous function

puts "\n** use do...end for multiple lines code **"
[1,2,3].each do |num|
  print "#{num}!"
end

puts "\n**Array using yield**"
class Array
  def my_each
    i = 0
    while i < self.size
      yield(self[i] * 2) # return each element
      i += 1
    end
    select # return itself
  end
end

[1,2,3].my_each {|num| print "#{num},"}

puts "\n** sort by the length **"
values = ["bb", "a", "ccc"]
puts result = values.sort {|left, right| left.length <=> right.length} # <=> means not equal
puts result = values.sort {|left, right| right.length <=> left.length}

puts "\n** numeric sort **"
numbers = [2,1,3]
puts numbers.sort {|x,y| x <=> y}
puts numbers.sort {|x,y| y <=> x}

# output:
# ** use {} for one line code **
# 1!2!3!
# ** use do...end for multiple lines code **
# 1!2!3!
# **Array using yield**
# 2,4,6,
# ** sort by the length **
# a
# bb
# ccc
# ccc
# bb
# a
#
# ** numeric sort **
# 1
# 2
# 3
# 3
# 2
# 1
