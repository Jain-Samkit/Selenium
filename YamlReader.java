import java.util.List;
import java.util.Map;

public class YamlReader {
private String url;
private String testData;
private String testResult;
private String searchKey;
private String language;
private List<Map<String,String>> pageTitles;

public void setUrl(String url)
{
	this.url=url;
	}
public void setSearchKey(String searchKey)
{
	this.searchKey=searchKey;
	}
public void setLanguage(String language)
{
	this.language=language;
	}

public void setTestData(String testData)
{
	this.testData= testData;
	}
public void setTestResult(String testResult)
{
	this.testResult =testResult; 
	}
public void setPageTitles(List<Map<String,String>> pageTitles)
{
	this.pageTitles= pageTitles;
}

public String getUrl()
{
	return this.url;
}
public String getLanguage()
{
	return this.language;
}

public String getSearchKey()
{
	return this.searchKey;
}

public String getTestData()
{ return testData;
}

public String getTestResult()
{ return testResult;
	}

public List<Map<String,String>> getPageTitles()
{
	return pageTitles;
}

}
