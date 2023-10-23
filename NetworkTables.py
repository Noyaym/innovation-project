from networktables import NetworkTables

ip = "ה-ip"
angle = 0


NetworkTables.initialize(server=ip)

NetworkTables.putNumber("myAngle", angle)

received_value = NetworkTables.getNumber("myAngle", 0)

NetworkTables.shutdown()