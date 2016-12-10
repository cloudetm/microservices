class Vehicle
  def initialize(make)
    @make = make
  end
  attr_accessor :make
  def accelerate
    puts "Moving faster"
  end
  def brake
    puts "Slowing down"
  end
  def start
    puts "The engine is now running"
  end
  def to_s
    "Make is #{@make}"
  end
end

class Motorcyle < Vehicle
  def initialize(make, model)
    super(make)
    @model = model
  end
  def engage_clutch
    puts "Clutch engaged"
  end
  def to_s # overriding Vehicle.to_s
    "Make is #{@make} and model is #{@model}"
  end
end

puts "** Motorcyle **"
motorcyle = Motorcyle.new("Harly", "Roadster")
puts motorcyle.accelerate
puts motorcyle.engage_clutch
puts motorcyle.make
puts motorcyle.to_s

# output:
# ** Motorcyle **
# Moving faster
#
# Clutch engaged
#
# Harly
# Make is Harly and model is Roadster
