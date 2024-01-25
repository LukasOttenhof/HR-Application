import android.content.Context
import com.example.wolseytechhr.EmployeeFiles
import com.example.wolseytechhr.EmployeeFilesEntity
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class EmployeeFilesTest {
    @Test
    fun testRetrieveInfoOnline() {
        // Mock the context
        val mockContext: Context = Mockito.mock(Context::class.java)

        // Mock the isOnline method to return true
        Mockito.`when`(EmployeeFiles.isOnline(mockContext)).thenReturn(true)

        // Mock the getUserRawInfoFromServer method
        val employeeFilesMock: EmployeeFiles = Mockito.spy(EmployeeFiles("auth_code", mockContext))
        Mockito.doNothing().`when`(employeeFilesMock).getUserRawInfoFromServer()

        // Perform the test
        val result = employeeFilesMock.retrieveInfo(mockContext)

        // Assertions
        Assert.assertNotNull(result)
    }

    @Test
    fun testRetrieveInfoOffline() {
        // Mock the context
        val mockContext: Context = Mockito.mock(Context::class.java)

        // Mock the isOnline method to return false
        Mockito.`when`(EmployeeFiles.isOnline(mockContext)).thenReturn(false)

        // Mock the getEmployeeInfoFromDatabase method
        val employeeFilesMock: EmployeeFiles = Mockito.spy(EmployeeFiles("auth_code", mockContext))
        Mockito.`when`(employeeFilesMock.retrieveInfo(mockContext)).thenReturn(mockLocalData())
        // Perform the test
        val result = employeeFilesMock.retrieveInfo(mockContext)

        // Mock the context
        val mockContext2: Context = Mockito.mock(Context::class.java)

        // Mock the isOnline method to return false
        Mockito.`when`(EmployeeFiles.isOnline(mockContext2)).thenReturn(false)

        // Mock the getEmployeeInfoFromDatabase method
        val employeeFilesMock2: EmployeeFiles = Mockito.spy(EmployeeFiles("auth_code", mockContext))
        Mockito.`when`(employeeFilesMock.retrieveInfo(mockContext2)).thenReturn(mockLocalData())

        // Perform the test
        val result2 = employeeFilesMock2.retrieveInfo(mockContext)

        // Assertions
        Assert.assertNotNull(result)
        Assert.assertNotNull(result2)
    }

    // Helper method to mock local data
    private fun mockLocalData(): MutableList<Array<String>>? {
        val mockData: MutableList<Array<String>> = mutableListOf()

        // Mocking employee data, adjust as needed
        val employee1 = arrayOf("John Doe", "30", "Male", "Engineer")
        val employee2 = arrayOf("Jane Smith", "28", "Female", "Designer")

        // Adding mock data to the list
        mockData.add(employee1)
        mockData.add(employee2)

        return mockData
    }

}