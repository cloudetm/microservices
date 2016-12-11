puts "** class scope instance variable is not accessible inside class methods **"

class MyClass
  puts self
  @balance = 100
  def print_balance
    puts self
    puts self.instance_variable_get :@balance # class scope @balance is not accessible
  end
end

m = MyClass.new
m.print_balance

# output
# ** class scope instance variable is not accessible inside class methods **
# MyClass
# #<MyClass:0x007f8c2c88a5f8>
