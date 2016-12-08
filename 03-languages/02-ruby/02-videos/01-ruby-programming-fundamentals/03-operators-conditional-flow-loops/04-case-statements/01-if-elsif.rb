country = "United States"
configuration = "both"

if configuration == "reverse"
  puts "This is the country backwards"
  puts country.reverse
elsif configuration == "uppercase"
  puts "This is the country in all uppercase letters"
  puts country.upcase
elsif configuration == "both"
  puts "This is using both"
  puts country.reverse.upcase
end

# output:
# This is using both
# SETATS DETINU
