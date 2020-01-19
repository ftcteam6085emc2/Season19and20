The "slim" batteries are documented on REV site here:
http://www.revrobotics.com/rev-31-1302/

* Specification

    * Voltage: 12V
    * Capacity: 3000mAh
    * Weight: 567g
    * Maximum Dimensions: 113.5mm x 90.5mm x 23mm
    * Wire Gauge: 16 AWG
    * Connector: XT30
    * Replaceable Fuse: 20A ATM
    * Maximum Discharge Rate: 10C
        * While the battery cells are rated at a 10C (30A) discharge, the in-line fuse limits this to 20A.
        Wire Length (excluding XT30): 150mm

* Best Practices

All rechargeable batteries have a finite lifespan. Factors that affect lifespan
include the number of discharge/charge cycles and the average loading of the
battery. The following best practices can help maximize the lifespan of your
battery:

* Charge rate
    * Minimum: 1.5A
    * Maximum: 3.0A
    * Recommended: 1.8A or 2.0A
* Do not overcharge
    * Disconnect the battery from the charger once it indicates a full charge.
    * Typical charge time does not exceed 2 hours.
    * Do not charge a battery that hasn't been discharged significantly.
        * For example, running the robot under minimal load for a few minutes will not significantly discharge the battery.
    * Minimum no-load voltage: 9.0V
        * Discharging the battery past 9.0V can reduce the lifespan of the battery and can permanently damage the cells.
        * Periodic dips below 9.0V when under load is expected and OK.
            * For example, don't forget to unplug your battery after you are finished running the robot and don't run your robot until it completely stops responding!
* Temperature
    * Let the battery cool before and after charging.
    * The battery may feel warm after heavy loading or after charging. This is normal.


How to charge batteries

1. Hook up the battery to one of the empty charging ports
2. Press the Esc/Mode button and select PROGRAM SELECT NiMH Battery
3. Press the Start / Enter button
4. Press the Dec or Inc button until Ni-MH/Ni-CD Charge Man appears
5. Press the start/enter button to edit the parameters
6. Press the Dec or Inc button to adjust the number of amps to charge the battery.
    * Normally set this to 1.8 A - 2.0 A
    * If you are in a hurry you can push 6 amps into the battery and do a quick charge.
      It is not recommended that this happens very often.
7. Once the parameters are set push and hold the start/enter button and charging will start.