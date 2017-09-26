**_Tiny service - after-interview task_**


**Launch :** 
- Change db access settings in resources/db/db.properties
- Use mvn jetty:run to launch jetty server on 9095 (http://127.0.0.1:9095/resource/all)

**Actions**
- GET / (list of all counters)
- GET /name (get concrete counter)
- DELETE /name (delete counter)
- PUT /name (create or increment a counter) - PUT should be idempotent but in this case counter value is completely replaced with a new one > so maybe it's ok.

**Possible improvements :** 
- db connection pooling;
- handling of version conflict (optimistic locking);
- integration tests (there are only a few junit tests);
- use Response.status(xxx). instead of throwing exceptions;
- enable self-documenting of the service (swager, etc ...)