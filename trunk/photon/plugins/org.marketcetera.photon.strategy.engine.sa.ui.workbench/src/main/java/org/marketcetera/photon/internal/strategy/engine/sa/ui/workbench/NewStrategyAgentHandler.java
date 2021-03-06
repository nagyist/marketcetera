package org.marketcetera.photon.internal.strategy.engine.sa.ui.workbench;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.marketcetera.photon.commons.ui.workbench.SafeHandler;
import org.marketcetera.photon.strategy.engine.IStrategyEngines;
import org.marketcetera.photon.strategy.engine.sa.ui.NewStrategyAgentWizard;
import org.marketcetera.util.misc.ClassVersion;

/**
 * Handler for the {@code
 * org.marketcetera.photon.strategy.engine.sa.ui.workbench.
 * newStrategyAgent} command that launches the {@link NewStrategyAgentWizard}
 * and adds it to the current view.
 * 
 * @author <a href="mailto:will@marketcetera.com">Will Horn</a>
 * @version $Id: NewStrategyAgentHandler.java 16154 2012-07-14 16:34:05Z colin $
 * @since 2.0.0
 */
@ClassVersion("$Id: NewStrategyAgentHandler.java 16154 2012-07-14 16:34:05Z colin $")
public final class NewStrategyAgentHandler extends SafeHandler {

    @Override
    public void executeSafely(ExecutionEvent event) throws ExecutionException {
        IWorkbenchPart part = HandlerUtil.getActivePartChecked(event);
        NewStrategyAgentWizard newWizard = new NewStrategyAgentWizard(
                (IStrategyEngines) part, null);
        WizardDialog wizardDialog = new WizardDialog(HandlerUtil
                .getActiveShellChecked(event), newWizard);
        if (wizardDialog.open() == Window.OK) {
            if (part instanceof ISetSelectionTarget) {
                ((ISetSelectionTarget) part)
                        .selectReveal(new StructuredSelection(newWizard
                                .getResult()));
            }
        }
    }

}
