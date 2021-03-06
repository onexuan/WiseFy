package com.isupatches.wisefy;


import android.net.wifi.ScanResult;
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.base.TestUtils.TEST_TIMEOUT;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class SearchForAccessPointTests extends BaseAndroidJUnit4TestClass{

    @Test
    public void sync_failure_filterDuplicates_false_nullSSIDParam() {
        assertEquals(null, mWiseFy.searchForAccessPoint(null, TEST_TIMEOUT, false));
    }

    @Test
    public void sync_failure_filterDuplicates_true_nullSSIDParam() {
        assertEquals(null, mWiseFy.searchForAccessPoint(null, TEST_TIMEOUT, true));
    }

    @Test
    public void sync_failure_filterDuplicates_false_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(null, mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false));
    }

    @Test
    public void sync_failure_filterDuplicates_true_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(null, mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true));
    }

    @Test
    public void sync_failure_filterDuplicates_false() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);

        assertEquals(null, mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false));
    }

    @Test
    public void sync_failure_filterDuplicates_true() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);

        assertEquals(null, mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true));
    }

    @Test
    public void sync_success_filterDuplicates_false() {
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.SSID = TEST_SSID;

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);

        assertEquals(accessPoint, mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false));
    }

    @Test
    public void sync_success_filterDuplicates_true() {
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.SSID = TEST_SSID;
        accessPoint.level = -35;

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);

        assertEquals(accessPoint, mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true));
    }

    @Test
    public void async_failure_filterDuplicates_false_nullSSIDParam() {
        SearchForAccessPointCallbacks mockCallbacks = mock(SearchForAccessPointCallbacks.class);
        mWiseFy.searchForAccessPoint(null, TEST_TIMEOUT, false, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).searchForAccessPointWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void async_failure_filterDuplicates_false_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.searchForAccessPoint(null, TEST_TIMEOUT, false, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_filterDuplicates_true_nullSSIDParam() {
        SearchForAccessPointCallbacks mockCallbacks = mock(SearchForAccessPointCallbacks.class);
        mWiseFy.searchForAccessPoint(null, TEST_TIMEOUT, true, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).searchForAccessPointWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void async_failure_filterDuplicates_true_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.searchForAccessPoint(null, TEST_TIMEOUT, true, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_filterDuplicates_false_missingPrerequisite() {
        missingPrerequisite();
        SearchForAccessPointCallbacks mockCallbacks = mock(SearchForAccessPointCallbacks.class);
        mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).searchForAccessPointWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void async_failure_filterDuplicates_false_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_filterDuplicates_true_missingPrerequisite() {
        missingPrerequisite();
        SearchForAccessPointCallbacks mockCallbacks = mock(SearchForAccessPointCallbacks.class);
        mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).searchForAccessPointWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void async_failure_filterDuplicates_true_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_filterDuplicates_false() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);

        SearchForAccessPointCallbacks mockCallbacks = mock(SearchForAccessPointCallbacks.class);
        mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).accessPointNotFound();
    }

    @Test
    public void async_failure_filterDuplicates_false_nullCallback() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);

        try {
            mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_filterDuplicates_true() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);

        SearchForAccessPointCallbacks mockCallbacks = mock(SearchForAccessPointCallbacks.class);
        mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).accessPointNotFound();
    }


    @Test
    public void async_failure_filterDuplicates_true_nullCallback() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);

        try {
            mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success_filterDuplicates_false() {
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.SSID = TEST_SSID;
        accessPoint.level = -35;

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);

        SearchForAccessPointCallbacks mockCallbacks = mock(SearchForAccessPointCallbacks.class);
        mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).accessPointFound(accessPoint);
    }

    @Test
    public void async_success_filterDuplicates_false_nullCallback() {
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.SSID = TEST_SSID;
        accessPoint.level = -35;

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);

        try {
            mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success_filterDuplicates_true() {
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.SSID = TEST_SSID;
        accessPoint.level = -35;

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);

        SearchForAccessPointCallbacks mockCallbacks = mock(SearchForAccessPointCallbacks.class);
        mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).accessPointFound(accessPoint);
    }

    @Test
    public void async_success_filterDuplicates_true_nullCallback() {
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.SSID = TEST_SSID;
        accessPoint.level = -35;

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);

        try {
            mWiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
