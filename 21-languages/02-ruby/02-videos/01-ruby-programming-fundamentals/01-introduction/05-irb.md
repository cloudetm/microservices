# IRB

> `$ irb`

**Exit**

```
irb(main):024:0> exit
```

**Integer**

```
irb(main):003:0> 5+5
=> 10
irb(main):004:0> 5.0*3
=> 15.0
```

**String**

```
irb(main):001:0> "Hello world"
=> "Hello world"
irb(main):002:0> puts "Hello world"
Hello world
=> nil
irb(main):005:0> foo = "hello world"
=> "hello world"
irb(main):006:0> foo
=> "hello world"
irb(main):007:0> puts foo
hello world
=> nil
irb(main):008:0> foo2 = foo + foo
=> "hello worldhello world"
irb(main):009:0> foo2
=> "hello worldhello world"
irb(main):010:0> foo * 3
=> "hello worldhello worldhello world"
irb(main):011:0> bar = "how are you?"
=> "how are you?"
irb(main):013:0> foo + ", " + bar
=> "hello world, how are you?"
```

**Cast**

```
irb(main):014:0> "Tom is " + 5.to_s + " years old."
=> "Tom is 5 years old."
irb(main):016:0> "5".to_i + 3
=> 8
```

**Array**

```
irb(main):017:0> people = ["Tom", "Dick", "Harry"]
=> ["Tom", "Dick", "Harry"]
irb(main):018:0> people
=> ["Tom", "Dick", "Harry"]
irb(main):023:0> people[1]
=> "Dick"
irb(main):019:0> people.first
=> "Tom"
irb(main):020:0> people.last
=> "Harry"
irb(main):021:0> people.sort
=> ["Dick", "Harry", "Tom"]
irb(main):022:0> people.sort.first
=> "Dick"
```
