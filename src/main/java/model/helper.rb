def model name, &block
  m = Model.new(name)
  block.call(m)
  
  return m
end
