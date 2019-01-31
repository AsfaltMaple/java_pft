package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import com.lavasoft.GetIpLocation;
import com.lavasoft.GetLocation;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class IpServiceTests {

  @Test
  public void ipServiceTest() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("193.232.107.2");
    assertEquals(ipLocation.contains("RU"), true);
  }
}
