The sample of the failure when connecting with https on 64bit arch.

# Process to reproduce

Run the following command and click "Click me!" text on device.
(Need to install ant)

```
ant run64
```

After click the text (and send https request), it will crash without any stack trace.

# In 32bit

When running on 32bit machine (with the following command), it's OK.

```
ant run
```

# Robo VM version

robovm-1.0.0-SNAPSHOT built on 2015-01-29 (libgdx's nightly build)

# Symbolicate

https://github.com/garsue/robovm-https-64bit-failure-sapmle/blob/master/IOSDemo%20%202015-02-02%2010-17.crash
