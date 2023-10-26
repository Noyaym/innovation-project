import ntcore

inst = ntcore.NetworkTableInstance.getDefault()

inst.startClient4("example client")

# connect to a roboRIO with team number TEAM
inst.setServerTeam(5635)

# starting a DS client will try to get the roboRIO address from the DS application
inst.startDSClient()

inst.setServer("host", ntcore.NetworkTableInstance.kDefaultPort4)

# Get the table within that instance that contains the data. Correct the table name.
table = inst.getTable("datable")
x = 2
y = 1

while True:
    xPub = table.getEntry("x")
    yPub = table.getEntry("y")
    aPub = table.getEntry("angle2")
    

    # Publish values that are constantly increasing.
    xPub.setDouble(x)
    yPub.setDouble(y)
    aPub.setDouble(x)
    x += 0.05
    y += 1.0
