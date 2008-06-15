include Java

import java.awt.Color
import java.awt.geom.Rectangle2D

def fill_left_with color
  $g.setPaint(color)

  rect = Rectangle2D::Double.new(0, 0, 100, 200)
  $g.fill(rect)
end

def fill_center_with color
  $g.setPaint(color)

  rect = Rectangle2D::Double.new(100, 0, 100, 200)
  $g.fill(rect)
end

def fill_right_with color
  $g.setPaint(color)

  rect = Rectangle2D::Double.new(200, 0, 100, 200)
  $g.fill(rect)
end
