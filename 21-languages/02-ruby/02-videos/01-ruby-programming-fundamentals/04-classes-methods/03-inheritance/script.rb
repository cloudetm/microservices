class Vehicle
  def initialize(make)
    @make = make
  end
  def accelerate
    puts "Moving faster"
  end
  def brake
    puts "Slowing down"
  end
  def start
    puts "The engine is now running"
  end
end

class Car < Vehicle
  def initialize(make)
  end
  def open_door
    puts "The door is opened"
  end
  def close_door
    puts "The door is closed"
  end
end

class Motorcyle < Vehicle
  def initialize(make)
  end
  def engage_clutch
    puts "Clutch engaged"
  end
end

puts "** car **"
car = Car.new("BMW")
puts car.open_door
puts car.accelerate

puts "** Motorcyle **"
motorcyle = Motorcyle.new("Harly")
puts motorcyle.accelerate
puts motorcyle.engage_clutch

# output:
# ** car **
# The door is opened
#
# Moving faster
#
# ** Motorcyle **
# Moving faster
#
# Clutch engaged
