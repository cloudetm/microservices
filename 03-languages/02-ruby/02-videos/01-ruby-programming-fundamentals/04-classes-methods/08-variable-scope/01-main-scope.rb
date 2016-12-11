puts "** main scope - instance-variable is accessible inside main scope method **"
@balance = 100

def add_to_balance(amount)
  puts self.instance_variable_get :@balance
  mybalance = @balance
  puts "@balance scope = " + defined? @balance
  puts "mybalance scope = " + defined? mybalance
end

add_to_balance(10)

# output:
# ** main scope - instance-variable is accessible inside method **
# 100
# @balance scope = instance-variable
# mybalance scope = local-variable
