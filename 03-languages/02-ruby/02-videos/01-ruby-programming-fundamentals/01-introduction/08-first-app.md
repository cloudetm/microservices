# Your First Ruby App

```
$ irb
irb(main):001:0> print "Please enter your name:"
Please enter your name:=> nil
irb(main):002:0> name = gets.chomp <- read input string
Joe
=> "Joe"
irb(main):004:0> print "Please enter your weight with a decimal:"
Please enter your weight with a decimal:=> nil
irb(main):005:0> height = gets.to_f <- read input decimal
200
=> 200.0
irb(main):006:0> name
=> "Joe"
irb(main):007:0> height
=> 200.0
irb(main):008:0> print "Your name is #{name} and your weight is ", height * 0.92, " on Saturn.\n"
Your name is Joe and your weight is 184.0 on Saturn.
=> nil
irb(main):009:0> 
```
