package sql;

public class ThreadFactory
{
	private static final int MAX_SELECT_THREAD_INSTANCES = 1;
	private static SelectThread selectThreadInstance;
	private static int selectInstanceCount = 0;
	
    public static synchronized SelectThread getSelectThread()
	{
		if(selectInstanceCount < MAX_SELECT_THREAD_INSTANCES)
        {
			selectThreadInstance = new SelectThread();
        }
        
        return selectThreadInstance;
	}
    
    private static final int MAX_INSERT_THREAD_INSTANCES = 1;
	private static InsertThread insertThreadInstance;
    private static int insertInstanceCount = 0;
    
    public static synchronized InsertThread getInsertThread()
	{
		if(insertInstanceCount < MAX_INSERT_THREAD_INSTANCES)
        {
			insertThreadInstance = new InsertThread();
        }
        
        return insertThreadInstance;
	}
}
