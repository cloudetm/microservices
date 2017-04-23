@balance = 100

def add_to_balance(amount=0) # optional parameter - it has default value
  @balance += amount
end

def subtract_from_balance(amount)
  @balance -= amount
end

def print_balance
  puts "print_balance: Balance is $#{@balance}."
end

def get_balance
  "$" + @balance.to_s
  @balance # always returns the last line
end

def add_multiple_to_balance(*amounts) # *amounts is variable arguments
  sum = 0
  amounts.each do |a|
    @balance += a
    sum += a
  end
  puts "Just added $#{sum} to balance"
end

add_to_balance(18)
print_balance
subtract_from_balance(8)
print_balance
puts get_balance
add_multiple_to_balance(1,3,4)
print_balance
puts add_to_balance

# output:
# print_balance: Balance is $118.
# print_balance: Balance is $110.
# 110
# Just added $8 to balance
# print_balance: Balance is $118.
# 118
