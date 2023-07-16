import csv
import turtle

# 打开CSV文件
with open('F:\\java\\cleaningrobot\\output.csv', 'r') as csvfile:
    # 读取CSV文件
    reader = csv.reader(csvfile)
    # 转换为二维列表
    points = list(reader)

# 将坐标点转换为(float, float)类型
points = [(float(point[0]), float(point[1])) for point in points]


t = turtle.Turtle()
t.screen.register_shape("F:\\java\\cleaningrobot\\robot.gif")
t.shape("F:\\java\\cleaningrobot\\robot.gif")

turtle.screensize(canvwidth=points[0][0], canvheight=points[0][1], bg='white')
turtle.setup(points[0][0], points[0][1])
turtle.setworldcoordinates(0, 0, points[0][0], points[0][1])

t.penup()
t.ht()
t.speed(5)
for point in points[2:]:
    t.goto(point)
    t.dot(size=10)
t.goto(points[1])
t.st()
t.speed(1)
t.pendown()

# 依次连接坐标点
for point in points[2:]:
    t.goto(point)
    t.dot(size=10)



# 等待关闭窗口
turtle.done()
