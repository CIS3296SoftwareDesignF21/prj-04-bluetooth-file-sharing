
## Current State:

**App UI**  
Fragment and Adapter for simple recycle/scroll view. This is implemented via 'fragments/ScrollView.kt' and
'fragments/adapter/DeviceAdapter.kt'

**App Data**  
ViewModel to pass data from bluetooth class to UI fragment, currently holds a list of all discovered nearby devices.
This is implemented via 'viewModel.kt' which hold the BluetoothLiveData class.

**Bluetooth**  
BluetoothLe scanner to get nearby devices. This is implemented in 'bluetooth/BluetoothController.kt'


## Initial Task List:

**App UI**
- app navigation
- new fragment for creating messages
- settings (fragment? add a couple buttons?)

**App Data**
- solid message passing between bluetooth and ui
- abstract message class (incoming and outgoing packets)
- active connection manager
- message storage
- room database??

**Bluetooth**
- advertising with specific uuid and filtered scanning
- runnable thread for to wait on incoming connections
- runnable thread to attempt to make connections
- connection handlers
- sendBytes
- readBytes


**Helpful resources for android-dev and bluetooth:**

- <https://developer.android.com/courses/kotlin-android-fundamentals/overview>

- <https://developer.android.com/guide/topics/connectivity/bluetooth>