package robovm.misc;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;

public class IOSDemo extends UIApplicationDelegateAdapter {

    private UIWindow window = null;
    private int clickCount = 0;

    @Override
    public boolean didFinishLaunching(UIApplication application,
            UIApplicationLaunchOptions launchOptions) {

        final UIButton button = UIButton.create(UIButtonType.RoundedRect);
        button.setFrame(new CGRect(115.0f, 121.0f, 91.0f, 37.0f));
        button.setTitle("Click me!", UIControlState.Normal);

        button.addOnTouchUpInsideListener((UIControl control, UIEvent event) -> {
            StringBuilder sb = new StringBuilder("Click #" + (++clickCount));
            try {
                URLConnection conn = new URL("https://www.google.com/").openConnection();
                try (InputStream inputStream = conn.getInputStream()) {
                    byte[] b = new byte[10];
                    inputStream.read(b);
                    final String string = new String(b);
                    Foundation.log(string);
                    sb.delete(0, sb.length()).append(string);
                }
            } catch (MalformedURLException ex) {
                sb.delete(0, sb.length()).append(ex.getMessage());
            } catch (IOException ex) {
                sb.delete(0, sb.length()).append(ex.getMessage());
            } finally {
                button.setTitle(sb.toString(), UIControlState.Normal);
            }
        });

        window = new UIWindow(UIScreen.getMainScreen().getBounds());
        window.setBackgroundColor(UIColor.lightGray());
        window.addSubview(button);
        window.makeKeyAndVisible();

        return true;
    }

    public static void main(String[] args) {
        try (NSAutoreleasePool pool = new NSAutoreleasePool()) {
            UIApplication.main(args, null, IOSDemo.class);
        }
    }
}
