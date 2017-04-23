# initialize = constructor

class Customer
  def initialize(customer_id, name, city)
    @customer_id = customer_id
    @name = name
    @city = city
  end
  def customer_id=(customer_id)
    @customer_id = customer_id # @@customer_id is local variable
  end
  def customer_id
    @customer_id
  end

  attr_reader :city
  attr_writer :city

  attr_accessor :name

  attr_accessor :zipcode
end

puts "** Customer1 **"
customer1 = Customer.new(10, "John Doe", "Denver")
puts customer1.customer_id # 10
puts customer1.name # John Doe
puts customer1.city # Denver

puts "** Customer2 **"
customer2 = Customer.new(11, "Tom Lee", "Seattle")
puts customer2.zipcode # empty
