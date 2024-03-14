package com.android.mystoryappcompose.ui.invoice

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.mystoryappcompose.ui.theme.MyStoryAppComposeTheme

// Data class untuk item barang
data class InvoiceItem(val name: String, val quantity: Int, val price: Double)

// Data class untuk Invoice
data class Invoice(val number: String, val items: List<InvoiceItem>)

@Composable
fun InvoiceScreen(invoice: Invoice, invoicesViewModel: InvoiceViewModel) {

    val invoice = invoicesViewModel.invoice.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Tampilkan item-item pada invoice menggunakan LazyColumn
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(invoice.items) { item ->
                InvoiceItemRow(item = item)
            }
        }

        // Tampilkan total pembayaran
        Text(
            text = "Total: $${calculateTotal(invoice.items)}",
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .align(Alignment.End)
                .padding(vertical = 16.dp)
        )
    }
}

@Composable
fun InvoiceItemRow(item: InvoiceItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Tampilkan informasi mengenai item pada invoice
        Text(text = item.name)
        Text(text = "${item.quantity} x $${item.price}")
    }
}

// Fungsi untuk menghitung total pembayaran pada invoice
fun calculateTotal(items: List<InvoiceItem>): Double {
    var total = 0.0
    for (item in items) {
        total += item.quantity * item.price
    }
    return total
}

@Preview
@Composable
fun PreviewInvoice() {
    MyStoryAppComposeTheme {
        Surface {
            val sampleInvoice = Invoice(
                number = "INV-001",
                items = listOf(
                    InvoiceItem("Item A", 2, 25.0),
                    InvoiceItem("Item B", 1, 15.0),
                    InvoiceItem("Item C", 3, 10.0)
                )
            )
            InvoiceScreen(invoice = sampleInvoice)
        }
    }
}

@Composable
fun InvoiceScreen(orderId: String, date: String) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        InvoiceItem("Order ID:", orderId)
        InvoiceItem("Waktu:", date)
    }
}

@Composable
fun InvoiceItem(title: String, value: String) {
    Column(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body2
        )
    }
}

@Preview
@Composable
fun PreviewInvoiceScreen() {
    MyStoryAppComposeTheme {
        Surface {
            InvoiceScreen(
                orderId = "230905GW7G8FFK",
                date = "22/09/23"
            )
        }
    }
}

@Composable
fun InvoiceDetail() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(spacing.medium)
    ) {

        Column(modifier = Modifier.weight(0.4f)) {
            androidx.compose.material3.Text(
                text = "Grand Total",
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = invoice.value.customer?.name.toStringOrEmpty(),
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = invoice.value.customer?.address.toStringOrEmpty(),
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )

        }
        Column(modifier = Modifier.weight(0.6f)) {
            androidx.compose.material3.Text(
                text = "date",
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = invoice.value.createdAt.toDateTime(),
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }

}
