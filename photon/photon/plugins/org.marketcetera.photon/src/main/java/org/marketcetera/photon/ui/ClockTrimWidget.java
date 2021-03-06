package org.marketcetera.photon.ui;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.menus.WorkbenchWindowControlContribution;
import org.marketcetera.core.ClassVersion;
import org.marketcetera.core.ThreadLocalSimpleDateFormat;

/**
 * Places the clock widget in the bottom status bar of the Photon client. Sets
 * up the clock to be updated every second.
 * 
 * @author toli
 * @version $Id: ClockTrimWidget.java 16154 2012-07-14 16:34:05Z colin $
 */

@ClassVersion("$Id: ClockTrimWidget.java 16154 2012-07-14 16:34:05Z colin $")
public class ClockTrimWidget extends WorkbenchWindowControlContribution {
	private Label clockValue;
	private static final ThreadLocalSimpleDateFormat DATE_FORMAT_LOCAL = new ThreadLocalSimpleDateFormat(
			"MMM d HH:mm:ss z"); //$NON-NLS-1$
	private static final Timer timer = new Timer("ClockUpdateTimer"); //$NON-NLS-1$
	private TimerTask task;

	@Override
	public void dispose() {
		if (task != null) {
			task.cancel();
			task = null;
		}
		super.dispose();
	}

	@Override
	protected Control createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		clockValue = new Label(composite, SWT.NONE);
		clockValue.setText(DATE_FORMAT_LOCAL.get().format(new Date()));
		GridLayoutFactory.swtDefaults().generateLayout(composite);

		task = new TimerTask() {
			public void run() {
				if (!clockValue.isDisposed()) {
					clockValue.getDisplay().asyncExec(new Runnable() {
						public void run() {
							if (!clockValue.isDisposed()) {
								clockValue.setText(DATE_FORMAT_LOCAL.get()
										.format(new Date()));
							}
						}
					});
				}
			}
		};
		timer.schedule(task, 0, 1000);
		return composite;
	}
}
