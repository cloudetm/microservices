o = Object.new
puts o.object_id # 70117039949900
puts "o.class=#{o.class}" # o.class=Object

class Customer
  def customer_id=(customer_id)
    @customer_id = customer_id
  end
  def customer_id
    @customer_id
  end

  attr_reader :city
  attr_writer :city

  attr_accessor :name
end

puts "** customer1 **"
customer1 = Customer.new
puts customer1.object_id # 70117039949440
puts "c.class=#{customer1.class}" # c.class=Customer
puts customer1.customer_id = 10 # 10
puts customer1.customer_id # 10

puts customer1.city="New York City" # New York City
puts customer1.city # New York City

puts customer1.name = "John Doe" # John Doe
puts customer1.name # John Doe

puts "\n** customer2 **"
customer2 = Customer.new
puts customer2.object_id # 70117039949060
puts customer2.name = "Tom Lee" # Tom Lee
puts customer2.customer_id = 11 # 11
puts customer2.city = "Houston" # Houston

puts customer2 # #<Customer:0x007f8aca848a08>
