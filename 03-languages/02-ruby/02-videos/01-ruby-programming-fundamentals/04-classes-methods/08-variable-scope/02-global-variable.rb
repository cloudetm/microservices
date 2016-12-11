$grand_total = 1000

class MyClass
  puts "class scope - grand_total scope = " + defined? $grand_total
  def printme
    puts "method scope - grand_total scope = " + defined? $grand_total
  end
end

puts self
puts "main scope - grand_total scope = " + defined? $grand_total

m = MyClass.new
m.printme

# output:
# class scope - grand_total scope = global-variable
# main
# main scope - grand_total scope = global-variable
# method scope - grand_total scope = global-variable
