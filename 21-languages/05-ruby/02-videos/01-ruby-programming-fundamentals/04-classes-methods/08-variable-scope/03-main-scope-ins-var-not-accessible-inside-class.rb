puts "** main scope instance variable is not accessible inside class and class methods **"
@balance = 100

class MyClass
  puts self
  puts self.instance_variable_get :@balance # main scope @balance is not accessible
  def print_balance
    puts self
    puts self.instance_variable_get :@balance # main scope @balance is not accessible
  end
end

m = MyClass.new
m.print_balance

# output:
# ** main scope instance variable is not accessible inside class and class methods **
# MyClass
#
# #<MyClass:0x007fec3915e5c8>
