import ntcore




angle = 0

inst = ntcore.NetworkTableInstance.getDefault()

# start a NT4 client
inst.startClient4("example client")

# connect to a roboRIO with team number TEAM
inst.setServerTeam(5635)

# starting a DS client will try to get the roboRIO address from the DS application
inst.startDSClient()

# connect to a specific host/port
inst.setServer("host", ntcore.NetworkTableInstance.kDefaultPort4)
# Get the default instance of NetworkTables that was created automatically
# when the robot program starts

# Get the table within that instance that contains the data. There can
# be as many tables as you like and exist to make it easier to organize
# your data. In this case, it's a table called datatable.
table = inst.getTable("datatable")

# Start publishing topics within that table that correspond to the X and Y values
# for some operation in your program.
# The topic names are actually "/datatable/x" and "/datatable/y".
angle = table.getDoubleTopic("x").publish()


